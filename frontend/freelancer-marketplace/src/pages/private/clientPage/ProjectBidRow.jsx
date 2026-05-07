import useProjectsStore from "../../../store/projectsStore";

const ProjectBidRow = ({ bid, index, register, projectId }) => {
  const { clientProjects } = useProjectsStore((state) => state);
  const {
    bidId,
    bidStatus,
    amount,
    freelancer: { firstName, lastName, email },
  } = bid;

  const project = clientProjects.find(
    (pr) => pr.projectId === Number(projectId),
  );
  const isDisabled =
    project?.projectStatus !== "OPEN" || bidStatus === "CANCELED";

  return (
    <tr>
      <th>{index}</th>
      <td className="underline">
        <p>{firstName + " " + lastName}</p>
      </td>
      <td className="flex justify-center">
        <div className="text-cyan-500 font-bold bg-cyan-400/15 p-2 rounded-xl w-fit">
          {bidStatus}
        </div>
      </td>
      <td>${amount}</td>
      <td>{email}</td>
      <td className="text-center">
        <p className="bg-gray-300/20 rounded-4xl max-fit p-1">2026-05-07</p>
      </td>
      <td className="text-center">
        <input
          type="radio"
          value={bidId}
          disabled={isDisabled}
          {...register("selectedBid")}
        />
      </td>
    </tr>
  );
};

export default ProjectBidRow;
