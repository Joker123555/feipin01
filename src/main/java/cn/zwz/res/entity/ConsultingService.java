package cn.zwz.res.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_consulting_service")
@TableName("a_consulting_service")
@ApiModel(value = "资源回收咨询")
public class ConsultingService extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "咨询标题")
    private String title;

    @ApiModelProperty(value = "咨询内容")
    private String content;

    @ApiModelProperty(value = "咨询人ID")
    private String userId;

    @ApiModelProperty(value = "咨询人")
    private String userName;

    @ApiModelProperty(value = "是否回复")
    private Boolean replyFlag;

    @ApiModelProperty(value = "回复人ID")
    private String replyId;

    @ApiModelProperty(value = "回复人姓名")
    private String replyName;

    @ApiModelProperty(value = "回复内容")
    private String replyContent;

    @ApiModelProperty(value = "回复时间")
    private String replyTime;
}