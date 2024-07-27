package com.example.demo.serviceImpl;

import com.example.demo.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConfigServiceImpl implements ConfigService {


    @Override
    public void prepareConfig() {
        /*String link = "http://localhost/config/7112f-6789-23er-34178lfr";
        URI.create(link);
       log.info("HERE THE CONFIGURATION IS BEING PREPARED........ "+URI.create(link));*/

        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("linkedDevice", "1");
        myMap.put("customerId", "N234");
        myMap.put("imei", "1224");
        myMap.put("vin", "TESTSTGU00000003");

        format(myMap.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()),'&',"UTF-8");
    }

    public static String format(
            final List<? extends NameValuePair> parameters,
            final char parameterSeparator,
            final String charset) {
        final StringBuilder result = new StringBuilder();
        for (final NameValuePair entry : parameters) {
            final String encodedName = StringUtils.newStringUtf8(StringUtils.getBytesUtf8(entry.getName()));
            final Object value = entry.getValue();
            final String encodedValue = StringUtils.newStringUtf8(StringUtils.getBytesUtf8(entry.getValue()));//value.toString();
            if (!result.isEmpty()) {
                result.append(parameterSeparator);
            }
            result.append(encodedName);
            if (encodedValue != null) {
                result.append("=");
                result.append(encodedValue);
            }
        }
        log.info("----------> VALUE ----------> "+ result.toString());
        return result.toString();
    }

}
