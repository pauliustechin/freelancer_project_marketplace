import { MdEdit } from "react-icons/md";
import { MdDeleteOutline } from "react-icons/md";

const ContractsRow = ({ contract, index }) => {
  const { projectName, escrowStatus, agreedAmount, escrowAmount } =
    contract || {};

  return (
    <>
      <tr>
        <td>{index + 1}</td>
        <td className="underline">
          <p>{projectName}</p>
        </td>
        <td className="flex justify-center">${agreedAmount}</td>
        <td>
          <div className="text-cyan-500 font-bold bg-cyan-400/15 p-2 rounded-xl w-fit">
            {escrowStatus}
          </div>
        </td>
        <td>
          <div className="flex justify-center">$ {escrowAmount}</div>
        </td>
        <td className="flex gap-2 items-center justify-end">
          <MdEdit />
          <MdDeleteOutline />
        </td>
      </tr>
    </>
  );
};

export default ContractsRow;
