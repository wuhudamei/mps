/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/1 19:42:41                            */
/*==============================================================*/


drop table if exists biz_wall_floor_tile_order_count;

drop table if exists biz_wall_floor_tile_recheck;

drop table if exists biz_wall_floor_tile_recheck_cnfg;

drop table if exists biz_wall_floor_tile_return;

/*==============================================================*/
/* Table: biz_order_wall_floor_tile��modify��                     */
/*==============================================================*/
alter table biz_order_wall_floor_tile
add   is_count_square                char(1)         comment '�Ƿ�����������ӣ� -- \'0.��1.��',
add   unit_square                    decimal(8,2)    comment '��λ�������ӣ� -- \'';

/*==============================================================*/
/* Table: biz_purchase_wall_floor_tile��modify2��                 */
/*==============================================================*/
alter table biz_purchase_wall_floor_tile
add   is_count_square                char(1)         comment '�Ƿ�����������ӣ� -- \'0.��1.��',
add   unit_square                    decimal(8,2)    comment '��λ�������ӣ� -- \'',
add   apply_square                   decimal(8,2)    comment '�����������ӣ� -- \'';

/*==============================================================*/
/* Table: biz_wall_floor_tile_order_count                       */
/*==============================================================*/
create table biz_wall_floor_tile_order_count
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   square_budget                  decimal(8,2)    comment 'Ԥ����� -- \'',
   square_purchase_total          decimal(8,2)    comment '�ɹ��ϼ���� -- \'',
   square_return                  decimal(8,2)    comment '�˻���� -- \'',
   square_purchase_real           decimal(8,2)    comment '�ɹ�ʵ����� -- \'',
   square_settle                  decimal(8,2)    comment '������� -- \'',
   square_measure                 decimal(8,2)    comment 'ʵ����� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_wall_floor_tile_recheck                           */
/*==============================================================*/
create table biz_wall_floor_tile_recheck
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   square_budget                  decimal(8,2)    comment 'Ԥ����� -- \'',
   square_quota                   decimal(8,2)    comment '������� -- \'',
   square_purchase                decimal(8,2)    comment 'ʵ���µ���� -- \'',
   square_measure                 decimal(8,2)    comment 'ʵ����� -- \'',
   plan_measure_date              date            comment '�ƻ��������� -- \'',
   real_measure_date              date            comment 'ʵ�ʲ������� -- \'',
   measure_remarks                varchar(1000)   comment 'ʵ��˵�� -- \'',
   price                          decimal(8,2)    comment 'ǽ��ש���� -- \'',
   assess_square_error_1          decimal(8,2)    comment '����������1 -- \'',
   assess_square_error_2          decimal(8,2)    comment '����������2 -- \'',
   assess_amount_1                decimal(8,2)    comment '���˽��1 -- \'',
   assess_amount_2                decimal(8,2)    comment '���˽��2 -- \'',
   assess_person_name_1           varchar(20)     comment '������������1 -- \'',
   assess_person_name_2           varchar(20)     comment '������������2 -- \'',
   status                         varchar(20)     comment '״̬ -- \'',
   status_describe                varchar(1000)   comment '״̬���� -- \'',
   status_datetime                datetime        comment '״̬����ʱ�� -- \'',
   status_operate_employee_id     int(11)         comment '״̬������Ա��id -- \'',
   recheck_remarks                varchar(1000)   comment '���߱�ע -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_wall_floor_tile_recheck_cnfg                      */
/*==============================================================*/
create table biz_wall_floor_tile_recheck_cnfg
(
   price                          decimal(8,2)    comment '���ϵ��� -- \'',
   square_over_max_rate           decimal(6,4)    comment '����������ޱ��� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \''
);

/*==============================================================*/
/* Table: biz_wall_floor_tile_return                            */
/*==============================================================*/
create table biz_wall_floor_tile_return
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   square_return                  decimal(8,2)    comment '�˻���� -- \'',
   return_datetime                datetime        comment '�˻�����ʱ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

