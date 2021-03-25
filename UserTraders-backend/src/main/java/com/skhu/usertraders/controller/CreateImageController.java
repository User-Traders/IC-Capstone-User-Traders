package com.skhu.usertraders.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api")

@Slf4j
public class CreateImageController {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, path = "/image")
    public String fileupload(@RequestParam Map params, @RequestBody List<MultipartFile> files) {
        log.info("파일사이즈"+files.size());
        try {
            for (int i = 0; i < files.size(); i++) {
                files.get(i).transferTo(new File("C:\\Users\\jaebin2\\Documents\\IC-Capstone-User-Traders\\UserTraders-backend\\src\\main\\resources\\static\\images\\" + files.get(i).getOriginalFilename()));
                log.info("완료?");
            }
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return "file upload";
    }
    //  배포시 상대경로 :

}