package connection;

import java.io.Serializable;

public interface Response extends Serializable {
    String getMessage();
    Status getStatus();
}
