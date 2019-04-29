package com.project.Controller;

import com.project.Model.DTO.ReplyRequestDTO;
import com.project.Model.DTO.ReplyResponseDTO;
import com.project.Service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("posts/replies")
public class ReplyController {

    private ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            consumes="application/json",
            produces = "application/json")
    public ResponseEntity<ReplyResponseDTO> post(@RequestHeader("userId") int userId, @RequestBody ReplyRequestDTO replyRequestDTO)
    {
        return new ResponseEntity<>(replyService.reply(userId,replyRequestDTO), HttpStatus.CREATED);
    }
}
