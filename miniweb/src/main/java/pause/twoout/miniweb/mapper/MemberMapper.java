package pause.twoout.miniweb.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import twoout.miniweb.model.Member;

@Mapper
public interface MemberMapper {
	ArrayList<Member> searchall();
	ArrayList<Member> search();
}
