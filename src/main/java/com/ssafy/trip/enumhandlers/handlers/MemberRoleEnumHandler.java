package com.ssafy.trip.enumhandlers.handlers;

import com.ssafy.trip.enumhandlers.EnumHandler;
import com.ssafy.trip.member.model.roleenum.MemberRoleEnum;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(MemberRoleEnum.class)
public class MemberRoleEnumHandler extends EnumHandler<MemberRoleEnum> {
    public MemberRoleEnumHandler() {
        super(MemberRoleEnum.class);
    }
}