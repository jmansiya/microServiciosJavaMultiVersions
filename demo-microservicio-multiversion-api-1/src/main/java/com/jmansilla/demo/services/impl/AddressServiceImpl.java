package com.jmansilla.demo.services.impl;

import java.net.InetAddress;

import org.springframework.stereotype.Service;

import com.jmansilla.demo.services.AddressService;

/**
 * @author José Mansilla García-Gil
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

	/* (non-Javadoc)
	 * @see com.jmansilla.demo.services.AddressService#getServerAddress()
	 */
	@Override
	public String getServerAddress() throws Exception {
		// TODO Auto-generated method stub
		 final String serverAddress = InetAddress.getLocalHost().getHostAddress();

	     return serverAddress;
	}

}
