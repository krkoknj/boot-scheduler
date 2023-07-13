package com.boot.study.schedule;

import com.boot.study.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CronTable {
    @Value("${serviceKey}")
    private String encodingServiceKey;
    private final String businessDetailsUrl = "http://api.odcloud.kr/api/15083277/v1/uddi:84b05020-c26e-4a57-9a15-c60554764534";

    private final CompanyService companyService;


    private int count = 0;

    @Scheduled(cron = "0 0 17 * * ?") // 매일 17시 마다.
    public void seventeenHours() {

    }


    // 애플리케이션 시작 후 3초 후에 첫 실행, 그 후 매 20초마다 주기적으로 실행한다.
    @Scheduled(initialDelay = 3000, fixedDelay = 20000)
    public void otherJob() {
        count++;
        System.out.println("count = " + count);
        StringBuilder urlBuilder = new StringBuilder(businessDetailsUrl); /*URL*/
        try {
            // 파라미터 생성
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + encodingServiceKey);
            urlBuilder.append("&" + URLEncoder.encode("page", "UTF-8") +"=" + count);
            urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=10");
            System.out.println("urlBuilder = " + urlBuilder);
            // URL 객체 생성
            URL url = new URL(urlBuilder.toString());
            // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 5. 통신을 위한 메소드 SET.
            conn.setRequestMethod("GET");
            // 6. 통신을 위한 Content-type SET.
            conn.setRequestProperty("Content-type", "application/json");
            // 7. 통신 응답 코드 확인.
            System.out.println("Response code: " + conn.getResponseCode());
            // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            companyService.insertData(sb.toString());
            // 10. 객체 해제.
            rd.close();
            conn.disconnect();
            // 11. 전달받은 데이터 확인.
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
