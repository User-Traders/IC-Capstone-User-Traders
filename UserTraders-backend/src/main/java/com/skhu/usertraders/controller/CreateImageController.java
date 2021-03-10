package com.skhu.usertraders.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")

public class CreateImageController {
    static final String filePath = "C:\\SKHU-project\\IC-Capstone-User-Traders\\UserTraders-backend\\src\\main\\resources\\static\\images\\"; //파일 명

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, path = "/image")
    public String fileupload(@RequestBody List<MultipartFile> files) {
        try {
            for (int i = 0; i < files.size(); i++) {
                files.get(i).transferTo(new File(filePath + files.get(i).getOriginalFilename()));
            }
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return "file upload";
    }

}
