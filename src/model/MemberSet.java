package model;

import java.util.ArrayList;
import java.util.List;

public class MemberSet {
	
	private List<Member> memberList;

	public MemberSet() {
		memberList = new ArrayList<Member>();
	}
	
	public void addMember(Member member) {
		memberList.add(member);
	}
	public void deleteMember(int index) {
		memberList.remove(index);
	}
	public void deleteMember(Member member) {
		memberList.remove(member);
	}
	public Member getMember(int index) {
		return memberList.get(index);
	}
}
