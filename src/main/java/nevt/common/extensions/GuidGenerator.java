package nevt.common.extensions;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GuidGenerator {

    public static String generateGuid() {
        return UUID.randomUUID().toString();
    }
}
