package com.example.openapiproject.service;

import com.example.openapiproject.client.OpenApiClient;
import com.example.openapiproject.dto.AptDetailDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAPIReaderService {
    public void allAptSearchTest() {
        try {
            List<String> aptCodeList = getAptCodeList();
            List<AptDetailDto> aptDetailDtoList = getAptDetailDtos(aptCodeList);
            HSSFWorkbook workbook = getSheets(aptDetailDtoList);

            File file = new File("/Users/aswemake/desktop/docs/apt/aptInfo2.xls");
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static HSSFWorkbook getSheets(List<AptDetailDto> aptDetailDtoList) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        int rowIdx = 0;
        HSSFRow row = sheet.createRow(rowIdx++);
        HSSFCell cell = null;
        writeBaseXlsx(cell, row);
        writeXlsx(aptDetailDtoList, sheet, rowIdx);
        return workbook;
    }

    private static void writeXlsx(List<AptDetailDto> aptDetailDtoList, HSSFSheet sheet, int rowIdx) {
        HSSFRow row;
        HSSFCell cell;
        for (AptDetailDto aptDetailDto : aptDetailDtoList) {
            row = sheet.createRow(rowIdx++);

            cell = row.createCell(0);
            cell.setCellValue(aptDetailDto.getAptName());

            cell = row.createCell(1);
            cell.setCellValue(aptDetailDto.getAddr());

            cell = row.createCell(2);
            cell.setCellValue(aptDetailDto.getDongCnt());

            cell = row.createCell(3);
            cell.setCellValue(aptDetailDto.getPeopleCnt());

            cell = row.createCell(4);
            cell.setCellValue(aptDetailDto.getCompany());

            cell = row.createCell(5);
            cell.setCellValue(aptDetailDto.getTel());

            cell = row.createCell(6);
            cell.setCellValue(aptDetailDto.getFax());

            cell = row.createCell(7);
            cell.setCellValue(aptDetailDto.getUrl());

            cell = row.createCell(8);
            cell.setCellValue(aptDetailDto.getAptCode());

            cell = row.createCell(9);
            cell.setCellValue(aptDetailDto.getHoCnt());
        }
    }

    private static List<AptDetailDto> getAptDetailDtos(List<String> aptCodeList) throws IOException, JDOMException {
        List<AptDetailDto> aptDetailDtoList = new ArrayList<>();

        for (String aptCode : aptCodeList) {
            try {
                AptDetailDto aptDetailDto = getAptDetailDto(aptCode);
                aptDetailDtoList.add(aptDetailDto);
            } catch (NullPointerException e) {
                System.out.println("aptDetailDto Null");
            }
        }

        return aptDetailDtoList;
    }

    private static AptDetailDto getAptDetailDto(String aptCode) throws IOException, JDOMException {
        URL aptDetailUrl = new URL("http://apis.data.go.kr/1613000/AptBasisInfoService1/getAphusBassInfo?kaptCode=" + aptCode + "&ServiceKey=A2ekF8y2T6O8vG8Vj%2FfhI3jzj2k%2B7%2FuUO2WbkLDf3CCz0Y%2Bf90dMRHGa6%2BHxiBuCIw8neujkz26s%2BKr7Q%2BD7kQ%3D%3D");
        HttpURLConnection aptDetailConnection = (HttpURLConnection) aptDetailUrl.openConnection();
        Document aptDetailDoc = getDocumentBy(aptDetailConnection);
        return parseAptDetailXML(aptDetailDoc);
    }

    private static AptDetailDto parseAptDetailXML(Document aptDetailDoc) {
        Element root = aptDetailDoc.getRootElement();
        Element body = root.getChild("body");
        Element item = body.getChild("item");

        String aptName = "누락";
        String addr = "누락";
        String dongCnt = "누락";
        String peopleCnt = "누락";
        String company = "누락";
        String tel = "누락";
        String fax = "누락";
        String url = "누락";
        String aptCode = "누락";
        String hoCnt = "누락";

        try {
            if (item.getChild("kaptName") != null) {
                aptName = item.getChild("kaptName").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("aptName Null");
        }

        try {
            if (item.getChild("kaptAddr") != null) {
                addr = item.getChild("kaptAddr").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("kaptAddr Null");
        }

        try {
            if (item.getChild("kaptDongCnt") != null) {
                dongCnt = item.getChild("kaptDongCnt").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("KaptDongCnt Null");
        }

        try {
            if (item.getChild("kaptdaCnt") != null) {
                peopleCnt = item.getChild("kaptdaCnt").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("KaptdaCnt Null");
        }

        try {
            if (item.getChild("kaptBcompany") != null) {
                company = item.getChild("kaptBcompany").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("kaptBcompany Null");
        }

        try {
            if (item.getChild("kaptTel") != null) {
                tel = item.getChild("kaptTel").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("kaptTel Null");
        }

        try {
            if (item.getChild("kaptFax") != null) {
                fax = item.getChild("kaptFax").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("kaptFax Null");
        }

        try {
            if (item.getChild("kaptUrl") != null) {
                url = item.getChild("kaptUrl").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("kaptUrl Null");
        }

        try {
            if (item.getChild("codeAptNm") != null) {
                aptCode = item.getChild("codeAptNm").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("codeAptNm Null");
        }

        try {
            if (item.getChild("hoCnt") != null) {
                hoCnt = item.getChild("hoCnt").getText();
            }
        } catch (NullPointerException e) {
            System.out.println("hoCnt Null");
        }

        return AptDetailDto.builder()
                .aptName(aptName)
                .addr(addr)
                .dongCnt(dongCnt)
                .peopleCnt(peopleCnt)
                .company(company)
                .tel(tel)
                .fax(fax)
                .url(url)
                .aptCode(aptCode)
                .hoCnt(hoCnt)
                .build();
    }

    private static void writeBaseXlsx(HSSFCell cell, HSSFRow row) {
        cell = row.createCell(0);
        cell.setCellValue("단지명");

        cell = row.createCell(1);
        cell.setCellValue("법정동주소");

        cell = row.createCell(2);
        cell.setCellValue("동수");

        cell = row.createCell(3);
        cell.setCellValue("세대수");

        cell = row.createCell(4);
        cell.setCellValue("시공사");

        cell = row.createCell(5);
        cell.setCellValue("관리사무소연락처");

        cell = row.createCell(6);
        cell.setCellValue("관리사무소팩스");

        cell = row.createCell(7);
        cell.setCellValue("홈페이지주소");

        cell = row.createCell(8);
        cell.setCellValue("단지분류");

        cell = row.createCell(9);
        cell.setCellValue("호수");
    }

    private static List<String> getAptCodeList() throws IOException, JDOMException {
        URL aptCodeUrl = new URL("http://apis.data.go.kr/1613000/AptListService2/getTotalAptList?ServiceKey=3oVJJ7Ljmy3bgMmPMcvlWxp%2BoECSYXq3KyXUAjwAMJF%2B7lztGIi3uYEtW1ZNcKbvHf9aM%2FVsDqRuvjPrrh%2BstQ%3D%3D&pageNo=2&numOfRows=10000");
        HttpURLConnection aptCodeConnection = (HttpURLConnection) aptCodeUrl.openConnection();
        setHttpConnection(aptCodeConnection);
        Document aptCodeDoc = getDocumentBy(aptCodeConnection);

        return parseAptCodeXml(aptCodeDoc);
    }

    private static Document getDocumentBy(HttpURLConnection aptCodeConnection) throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(aptCodeConnection.getInputStream());
    }

    private static void setHttpConnection(HttpURLConnection httpUrlConnection) throws IOException {
        httpUrlConnection.setRequestProperty("Content-Type", "application/xml");
        httpUrlConnection.setRequestMethod("GET");
        httpUrlConnection.connect();
    }

    private static List<String> parseAptCodeXml(Document aptCodeDoc) {
        List<String> aptCodeList = new ArrayList<>();

        Element root = aptCodeDoc.getRootElement();
        Element body = root.getChild("body");
        Element items = body.getChild("items");
        List<Element> item = items.getChildren();

        for (Element element : item) {
            aptCodeList.add(element.getChild("kaptCode").getText());
        }
        return aptCodeList;
    }
}
