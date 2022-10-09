package twoout.miniweb.model;

import lombok.Data;

@Data
public class Member {
	String memberID, memberPW, memberName, nickName, phone, email, zipcode, address, buliding, membership;
}
