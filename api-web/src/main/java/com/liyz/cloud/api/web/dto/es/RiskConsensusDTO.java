package com.liyz.cloud.api.web.dto.es;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2020/1/16 16:33
 */
@Data
public class RiskConsensusDTO implements Serializable {
    private static final long serialVersionUID = -2006229914007431754L;

    private Long id;

    private String content;

    private Date createTime;

    private String finalTitle;

    private String name;

    private String pickedAbstract;

    private Date publishTime;

    private String remark;

    private Double sentimentScore;

    private Integer sentimentType;

    private String source;

    private String sourceType;

    private String title;

    private String webSite;
}
