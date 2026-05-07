import FreelancerBidTable from "./FreelancerBidTable";
import SideBar from "../../../components/shared/SideBar";

const FreelancerPage = () => {
  return (
    <SideBar role={"seller"}>
      <div className="justify-between mb-6 text-start">
          <h1 className="text-2xl font-bold">Freelancer dashboard</h1>
          <p>Manage your active bids.</p>
      </div>
      <hr />
      <div>
        <FreelancerBidTable></FreelancerBidTable>
      </div>
    </SideBar>
  );
};

export default FreelancerPage;
