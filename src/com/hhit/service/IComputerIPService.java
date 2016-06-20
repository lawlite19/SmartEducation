package com.hhit.service;

import java.util.List;

import com.hhit.base.IDaoSupport;
import com.hhit.entity.ComputerIP;

public interface IComputerIPService extends IDaoSupport<ComputerIP> {
	public List<ComputerIP> findbytestroomid(int testroomid);
	public ComputerIP findbyid(String id);
	public ComputerIP findbycomputerid(int computerid,int testroomid);
	public ComputerIP findbyip(String ip);
	public void deletebyid(String id);
}
