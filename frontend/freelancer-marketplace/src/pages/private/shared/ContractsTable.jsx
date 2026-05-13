import useUsersStore from "./../../../store/usersStore";
import { useEffect } from "react";
import useContractsStore from "../../../store/contractsStore";
import ContractsRow from "./ContractsRow";

const ContractsTable = () => {
  const { user } = useUsersStore((state) => state);
  const { fetchClientContracts, fetchFreelancerContracts, contracts } =
    useContractsStore((state) => state);

  console.log(contracts);

  useEffect(() => {
    if (user?.roles.includes("ROLE_CLIENT")) {
      fetchClientContracts();
    } else if (user?.roles.includes("ROLE_SELLER")) {
      fetchFreelancerContracts();
    }
  }, [user?.roles, fetchClientContracts, fetchFreelancerContracts]);

  return (
    <div className="text-slate-200 bg-slate-400 min-h-screen p-8">
      <div className="overflow-x-auto">
        <h1 className="text-start text-2xl font-bold p-2">Contracts</h1>
        <table className="table bg-slate-700">
          <thead className="text-gray-400">
            <tr>
              <th></th>
              <th>PROJECT</th>
              <th className="text-center">AGREED AMOUNT</th>
              <th>ESCROW STATUS</th>
              <th className="text-center">ESCROW AMOUNT</th>
              <th className="text-end">ACTIONS</th>
            </tr>
          </thead>
          <tbody>
            {contracts.map((contract, index) => (
              <ContractsRow
                key={contract.contractId}
                contract={contract}
                index={index}
              ></ContractsRow>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ContractsTable;
