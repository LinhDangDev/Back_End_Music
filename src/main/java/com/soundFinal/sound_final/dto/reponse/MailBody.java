package com.soundFinal.sound_final.dto.reponse;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
