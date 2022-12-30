package com.jiamusi.blog.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  BlogQueryParam {

    private  String title;

    private   String[]  tagsIds;

    private   Integer  type;


}
