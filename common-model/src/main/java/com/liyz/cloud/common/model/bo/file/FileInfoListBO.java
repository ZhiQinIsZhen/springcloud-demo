package com.liyz.cloud.common.model.bo.file;

import com.liyz.cloud.common.model.constant.file.FileType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2019/10/24 17:12
 */
@Data
public class FileInfoListBO implements Serializable {
    private static final long serialVersionUID = 4002855402379971257L;

    private FileType fileType;

    private List<FileInfoBO> files;
}
