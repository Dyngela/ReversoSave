package AFPA.CDA06.demo.Entities;

import java.sql.Connection;

public class Contract extends Client {

    private Integer IDContract;
    private String contractName;
    private Double contractAmount;


    public Contract(Integer IDContract, Integer ID, String contractName, Double contractAmount){
        super(ID);
        this.setIDContract(IDContract);
        this.setContractName(contractName);
        this.setContractAmount(contractAmount);
    }

    public Contract() {

    }

    public void setIDContract(Integer IDContract) {
        this.IDContract = IDContract;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Integer getIDContract() {
        return IDContract;
    }

    public String getContractName() {
        return contractName;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "IDContract=" + IDContract +
                ", contractName='" + contractName + '\'' +
                ", contractAmount=" + contractAmount +
                '}';
    }
}
