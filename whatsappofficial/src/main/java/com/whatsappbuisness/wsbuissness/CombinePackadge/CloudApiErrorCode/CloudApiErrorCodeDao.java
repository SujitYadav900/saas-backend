package com.whatsappbuisness.wsbuissness.CombinePackadge.CloudApiErrorCode;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Gateway;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class CloudApiErrorCodeDao {
    @Id
    private int id;
    private String code;
    private MessageStatus status;
    private String errorDesc;
    private Gateway gateway;
}
