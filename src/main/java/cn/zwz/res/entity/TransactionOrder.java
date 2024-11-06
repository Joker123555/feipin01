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
import java.math.BigDecimal;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_transaction_order")
@TableName("a_transaction_order")
@ApiModel(value = "资源交易单")
public class TransactionOrder extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易类型",notes = "0 求购单 | 1 销售单")
    private Integer type;

    @ApiModelProperty(value = "订单ID")
    private String orderId;

    @ApiModelProperty(value = "资源ID")
    private String resId;

    @ApiModelProperty(value = "资源名称")
    private String resName;

    @ApiModelProperty(value = "发布人ID")
    private String releaseId;

    @ApiModelProperty(value = "发布人姓名")
    private String releaseName;

    @ApiModelProperty(value = "交易量")
    private BigDecimal number;

    @ApiModelProperty(value = "交易价格")
    private BigDecimal price;

    @ApiModelProperty(value = "需求详情")
    private String content;

    @ApiModelProperty(value = "交易人ID")
    private String finishId;

    @ApiModelProperty(value = "交易人姓名")
    private String finishName;

    @ApiModelProperty(value = "交易时间")
    private String finishTime;
}