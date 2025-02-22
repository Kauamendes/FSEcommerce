package br.com.fs.ecommerce.foursalesecommerce.support;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;

import java.util.Locale;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class MessageBundle {

    public static String getMessage(String key, Object... argumentos) {
        try {
            MessageSource bean = ApplicationContextProvider.getBean(MessageSource.class);
            return bean.getMessage(key, argumentos, Locale.getDefault());
        } catch (Exception e) {
            log.error("Chave {} não reconhecida", key, e);
            return "Chave não reconhecida: " + key;
        }
    }

    public static String getMessageOrDefault(String key, String keyDefault, Object... argumentos) {
        try {
            MessageSource bean = ApplicationContextProvider.getBean(MessageSource.class);
            return bean.getMessage(key, argumentos, Locale.getDefault());
        } catch (Exception e) {
            return getMessage(keyDefault, argumentos);
        }
    }
}