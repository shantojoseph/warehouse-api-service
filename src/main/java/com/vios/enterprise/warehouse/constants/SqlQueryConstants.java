package com.vios.enterprise.warehouse.constants;

public class SqlQueryConstants {

    public static final String GET_PENDING_ACTIVATION_DEVICE = "SELECT d.* FROM DEVICE d,SIM s ,device_sim_mapping dsp WHERE d.id=dsp.device_id and s.id=dsp.sim_id and s.status=:status";

    public static final String GET_READY_FOR_SALE_DEVICE = "SELECT d.* FROM DEVICE d where  d.status=:status  order by d.created_date asc";

    public SqlQueryConstants() {

    }

}
