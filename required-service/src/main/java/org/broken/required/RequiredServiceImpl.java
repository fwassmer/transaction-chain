/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.broken.required;

import org.broken.interceptor.InterceptMe;
import org.broken.mandatory.MandatoryService;

import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;


public class RequiredServiceImpl implements RequiredService {

	MandatoryService mandatoryService;
	private UserTransaction userTransaction;

	@InterceptMe(isActive = true)
	@Transactional(Transactional.TxType.REQUIRED)
	public boolean callMandatoryFromRequired() {
		System.out.println("REQUIRED:");
		String tx = "none";
		try {
			tx = String.valueOf(userTransaction.getStatus());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		System.out.println("\tIn " + getClass().getCanonicalName() + " with tx status: " + tx);

		try {
			mandatoryService.doStuff();
			return true;
		} catch (Exception e) {
			System.out.println("\tfailure: " + e.getMessage());
			return false;
		}
	}

	@Transactional(Transactional.TxType.NOT_SUPPORTED)
	public boolean callMandatoryFromNotSupported() {
		System.out.println("NOT_SUPPORTED:");
		String tx = "none";
		try {
			tx = String.valueOf(userTransaction.getStatus());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		System.out.println("\tIn " + getClass().getCanonicalName() + " with tx status: " + tx);
		try {
			mandatoryService.doStuff();
			return true;
		} catch (Exception e) {
			System.out.println("\tfailure: " + e.getMessage());
			return false;
		}
	}

	@Transactional(Transactional.TxType.SUPPORTS)
	public boolean callMandatoryFromSupports() {
		System.out.println("SUPPORTS:");
		String tx = "none";
		try {
			tx = String.valueOf(userTransaction.getStatus());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		System.out.println("\tIn " + getClass().getCanonicalName() + " with tx status: " + tx);
		try {
			mandatoryService.doStuff();
			return true;
		} catch (Exception e) {
			System.out.println("\tfailure: " + e.getMessage());
			return false;
		}
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public boolean callMandatoryFromRequiresNew() {
		System.out.println("REQUIRES_NEW:");
		String tx = "none";
		try {
			tx = String.valueOf(userTransaction.getStatus());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		System.out.println("\tIn " + getClass().getCanonicalName() + " with tx status: " + tx);
		try {
			mandatoryService.doStuff();
			return true;
		} catch (Exception e) {
			System.out.println("\tfailure: " + e.getMessage());
			return false;
		}
	}

	@Transactional(Transactional.TxType.NEVER)
	public boolean callMandatoryFromNever() {
		System.out.println("NEVER:");
		String tx = "none";
		try {
			tx = String.valueOf(userTransaction.getStatus());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		System.out.println("\tIn " + getClass().getCanonicalName() + " with tx status: " + tx);
		try {
			mandatoryService.doStuff();
			return true;
		} catch (Exception e) {
			System.out.println("\tfailure: " + e.getMessage());
			return false;
		}
	}


	public void setMandatoryService(MandatoryService mandatoryService) {
		this.mandatoryService = mandatoryService;
	}

	public void setUserTransaction(UserTransaction userTransaction) {
		this.userTransaction = userTransaction;
	}

}