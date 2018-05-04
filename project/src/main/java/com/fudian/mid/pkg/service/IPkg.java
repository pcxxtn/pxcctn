package com.fudian.mid.pkg.service;

import com.fudian.mid.system.var.TransVariableSet;

public interface IPkg {
    /*
     *解包
     */
    public void unpack(String message, TransVariableSet VarSet);

    /*
     *打包
     * @return
     */
    public String pack(TransVariableSet VarSet);
}
