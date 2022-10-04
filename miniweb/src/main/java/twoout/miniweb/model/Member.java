package twoout.miniweb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	String memberID, memberPW, memberName, nickName, phone, email, zipcode, address, buliding, membership;
}
