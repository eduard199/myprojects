package com.project.Service;

import com.project.Model.DTO.ReplyRequestDTO;
import com.project.Model.DTO.ReplyResponseDTO;
import com.project.Model.Reply;

public interface ReplyService {
    ReplyResponseDTO reply(int userId, ReplyRequestDTO replyRequestDTO);
    Reply convertReplyRequestDTOToReply(ReplyRequestDTO replyRequestDTO);
    ReplyResponseDTO convertReplyToReplyResponseDTO(Reply reply);
}
