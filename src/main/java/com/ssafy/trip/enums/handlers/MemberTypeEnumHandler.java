package com.ssafy.trip.enums.handlers;

import com.ssafy.trip.member.model.enums.MemberTypeEnum;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(MemberTypeEnum.class)
public class MemberTypeEnumHandler extends EnumHandler<MemberTypeEnum> {
    public MemberTypeEnumHandler() {
        super(MemberTypeEnum.class);
    }
}