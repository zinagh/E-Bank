package tn.esprit.card_management.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.card_management.model.Attachement;

public interface IAttachementService {
    Attachement saveAttachment(MultipartFile file) throws Exception;
}
