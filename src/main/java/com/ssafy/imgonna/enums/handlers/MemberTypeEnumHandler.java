package com.ssafy.imgonna.enums.handlers;

import com.ssafy.imgonna.member.model.enums.MemberTypeEnum;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(MemberTypeEnum.class)
public class MemberTypeEnumHandler extends EnumHandler<MemberTypeEnum> {
    public MemberTypeEnumHandler() {
        super(MemberTypeEnum.class);
    }
}