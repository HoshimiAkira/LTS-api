package com.ha.ltschat.util;

import com.ha.ltschat.service.AzureBlobStorageService;
import com.ha.ltschat.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Base64;
@Component
public class RichTextUtils {

    private static final Pattern BASE64_IMAGE_PATTERN = Pattern.compile("data:image/(\\w+);base64,([^\"\\s]+)");
    @Autowired
    private AzureBlobStorageService azureBlobStorageService;
    @Autowired
    private MsgService msgService;
    public String extractAndSaveImages(String richText,String uuid) throws IOException {
        StringBuilder sb = new StringBuilder(richText);
        Matcher matcher = BASE64_IMAGE_PATTERN.matcher(sb);

        int i = 0;
        while (matcher.find()) {
            String fileType = matcher.group(1);
            String base64Data = matcher.group(2);


            byte[] imageData = Base64.getDecoder().decode(base64Data);
            String filename=uuid + i + "." + fileType;
            azureBlobStorageService.uploadFile(filename, imageData);
            String imageUrl = "https://ltscapicon.blob.core.windows.net/icon/"+filename;
            sb.replace(matcher.start(), matcher.end(), imageUrl);
            msgService.addMsgIcon(uuid,filename);
            matcher = BASE64_IMAGE_PATTERN.matcher(sb);

            i++;
        }

        return sb.toString();
    }
}