package com.xzx.commonsb.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xzx
 * @since 2021-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String attachmentNo;

    private String attachmentType;

    private String businessType;

    private String businessId;

    private String attachmentUrl;


}
