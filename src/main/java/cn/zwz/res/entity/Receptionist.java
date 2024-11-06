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
@Table(name = "a_receptionist")
@TableName("a_receptionist")
@ApiModel(value = "咨询接待人员")
public class Receptionist extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "接待人姓名")
    private String name;

    @ApiModelProperty(value = "接待人手机")
    private String mobile;

    @ApiModelProperty(value = "接待人签名")
    private String autograph;
}