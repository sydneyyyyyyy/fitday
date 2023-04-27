package com.fittrio.fitday.controller;

import com.fittrio.fitday.dto.BoardDTO;
import com.fittrio.fitday.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    @Qualifier("boardService")
    BoardService boardService;

    @PostMapping("/board/mission/form")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, @Valid BoardDTO dto, BindingResult result) {

        if (result.hasErrors()) {
            System.out.print(result);
            // 유효성 검사 실패 시 처리
            redirectAttributes.addFlashAttribute("message", "게시글 등록에 실패하였습니다.");
            return "redirect:/board/mission/form";
        }

        // 파일 업로드 처리
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "파일을 선택해주세요.");
            return "redirect:/board/mission/form";
        }

        try {
            // 업로드된 파일을 저장할 디렉토리 생성
            File uploadDir = new File("src/main/resources/uploads");
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // 업로드된 파일을 실제 디렉토리에 저장
            File uploadedFile = new File(uploadDir.getAbsolutePath(), file.getOriginalFilename());
            file.transferTo(uploadedFile);

            // 파일 업로드 성공 시 메시지 전달
            dto.setFile(file);
            boardService.insertMission(dto);

            redirectAttributes.addFlashAttribute("message", "파일과 게시글이 성공적으로 업로드 되었습니다!");
            return "redirect:/board/mission/list";

        } catch (IOException e) {
            // 파일 업로드 실패 시 에러 메시지 전달
            redirectAttributes.addFlashAttribute("message", "파일 업로드에 실패하였습니다.: " + e.getMessage());
            return "redirect:/board/mission/form";
        }


    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "10MB 이하 파일만 업로드 가능합니다.");
        return "redirect:/board/mission/form";
    }
}
