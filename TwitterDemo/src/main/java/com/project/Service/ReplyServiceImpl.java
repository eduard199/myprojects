package com.project.Service;

import com.project.Dao.ReplyDAO;
import com.project.Model.DTO.ReplyRequestDTO;
import com.project.Model.DTO.ReplyResponseDTO;
import com.project.Model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;

    @Autowired
    public ReplyServiceImpl(ReplyDAO replyDAO) {
        this.replyDAO = replyDAO;
    }

    public ReplyResponseDTO reply(int userId, ReplyRequestDTO replyRequestDTO) {

        Reply reply1= replyDAO.reply(userId,convertReplyRequestDTOToReply(replyRequestDTO));
        return convertReplyToReplyResponseDTO(reply1);
    }

    @Override
    public Reply convertReplyRequestDTOToReply(ReplyRequestDTO replyRequestDTO) {
        return Reply.builder()
                .withMessage(replyRequestDTO.getMessage())
                .withParentId(replyRequestDTO.getParentId())
                .withVisibility(replyRequestDTO.getVisibility())
                .build();
    }

    @Override
    public ReplyResponseDTO convertReplyToReplyResponseDTO(Reply reply) {
        return ReplyResponseDTO.builder()
                .withParentId(reply.getParentId())
                .withMessage(reply.getMessage())
                .withVisibility(reply.getVisibility())
                .build();
    }
}
