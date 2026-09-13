package com.msvc_test.application.usecases.utils;

import com.msvc_test.domain.exceptions.EmailSendException;
import com.msvc_test.domain.port.output.EmailExternalPort;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletionException;

@Slf4j
@Builder
public class SendEmail {

    public static void sendConfirnAccount(String email, String name, String token, String frontendUrl, EmailExternalPort emailExternalPort) {
        String subject = "Bienvenido a nuestro servicio";
        String body = """
                Hola %s,
                
                Bienvenido a nuestro servicio. Para activar tu cuenta, copia y pega el siguiente enlace en tu navegador:
                
                Token generado: %s
                
                %s/confirm
                
                Este enlace expirará en 10 minutos.
                
                Si no creaste una cuenta, puedes ignorar este mensaje.
                
                — El equipo
                """.formatted(name, token, frontendUrl);
        String htmlText = """
                <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 24px; border: 1px solid #e5e7eb; border-radius: 8px;">
                    <h2 style="color: #7c3aed;">¡Bienvenido, %s!</h2>
                    <p>Gracias por registrarte. Para activar tu cuenta, haz clic en el siguiente botón:</p>
                
                    <div style="text-align: center; margin: 32px 0;">
                        <a href="%s/auth/confirm"
                           style="background-color: #7c3aed; color: white; padding: 12px 24px;
                                  text-decoration: none; border-radius: 6px; font-weight: bold;
                                  display: inline-block;">
                            Confirmar cuenta
                        </a>
                    </div>
                
                    <p style="color: #6b7280; font-size: 14px;">
                        Este enlace expirará en <strong>10 minutos</strong>. Si no puedes hacer clic en el botón,
                        copia y pega este enlace en tu navegador:
                    </p>
                    <p style="word-break: break-all; font-size: 13px; color: #4b5563;">%s/auth/confirm</p>
                    <p style="word-break: break-all; font-size: 13px; color: #4b5563;">token : %s</p>
                    <hr style="border: none; border-top: 1px solid #e5e7eb; margin: 24px 0;" />
                    <p style="color: #9ca3af; font-size: 12px;">
                        Si no creaste una cuenta, puedes ignorar este mensaje.
                    </p>
                </div>
                """.formatted(name, frontendUrl, frontendUrl,token);
        try {
            emailExternalPort.sendEmail(email, "no-reply@msvc-test.com", subject, body, htmlText).join();
        } catch (CompletionException ex) {
            log.error("Error al enviar el correo de bienvenida a {}", email, ex);
            throw new EmailSendException("No se pudo enviar el correo de bienvenida a " + email);
        }
    }

    public static void sendResetPassword(String email, String name, String token, String frontendUrl, EmailExternalPort emailExternalPort) {
        String subject = "Restablecimiento de contraseña";
        String body = """
                Hola %s,
                
                Bienvenido a nuestro servicio. Para restablecer tu contraseña, copia y pega el siguiente enlace en tu navegador:
                
                Token generado: %s
                
                %s/reset-password
                
                Este enlace expirará en 10 minutos.
                
                Si no solicitaste restablecer password, puedes ignorar este mensaje.
                
                — El equipo
                """.formatted(name, token, frontendUrl);
        String htmlText = """
                <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 24px; border: 1px solid #e5e7eb; border-radius: 8px;">
                    <h2 style="color: #7c3aed;">¡Bienvenido, %s!</h2>
                    <p>Puedes restablecer tu password :</p>
                
                    <div style="text-align: center; margin: 32px 0;">
                        <a href="%s/auth/reset-password"
                           style="background-color: #7c3aed; color: white; padding: 12px 24px;
                                  text-decoration: none; border-radius: 6px; font-weight: bold;
                                  display: inline-block;">
                            Restablecer contraseña
                        </a>
                    </div>
                
                    <p style="color: #6b7280; font-size: 14px;">
                        Este enlace expirará en <strong>10 minutos</strong>. Si no puedes hacer clic en el botón,
                        copia y pega este enlace en tu navegador:
                    </p>
                    <p style="word-break: break-all; font-size: 13px; color: #4b5563;">%s/reset-password</p>
                    <p style="word-break: break-all; font-size: 13px; color: #4b5563;">token : %s</p>
                    <hr style="border: none; border-top: 1px solid #e5e7eb; margin: 24px 0;" />
                    <p style="color: #9ca3af; font-size: 12px;">
                        Si no solicitaste restablecer tu password, omite este mensaje.
                    </p>
                </div>
                """.formatted(name, frontendUrl, frontendUrl,token);
        try {
            emailExternalPort.sendEmail(email, "no-reply@msvc-test.com", subject, body, htmlText).join();
        } catch (CompletionException ex) {
            log.error("Error al enviar el correo de bienvenida a {}", email, ex);
            throw new EmailSendException("No se pudo enviar el correo de restablecer password a " + email);
        }
    }

}
