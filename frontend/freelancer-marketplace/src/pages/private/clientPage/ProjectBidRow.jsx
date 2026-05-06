

const ProjectBidRow = ({ bid, index, register }) => {

  const { bidId, freelancer, bidStatus, amount } = bid;

  return (
      <tr>
        <th>{index + 1}</th>
        <th>{freelancer.firstName + " " + freelancer.lastName}</th>
        <td>{freelancer.email}</td>
        <td>{bidStatus}</td>
        <td>{amount}</td>
        <td>
          <input
            type="radio"
            value={bidId}
            {...register("selectedBid")}
          />
        </td>
      </tr>
  );
};

export default ProjectBidRow;
