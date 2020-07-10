package com.pt.ptdealerproc.dto;


import com.pt.ptdealerproc.entity.ProcNodeWorker;
import com.pt.ptdealerproc.entity.ProcProcess;
import com.pt.ptdealerproc.entity.ProcProcessNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author wl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProcessDto extends ProcProcess {
	private List<ProcProcessNode> processNodes;
}
