package com.deyun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Calories extends Model implements Cloneable{

    private static final long serialVersionUID = 1L;

    private String name;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer calories;

    private String type;

    private String unit;


}
