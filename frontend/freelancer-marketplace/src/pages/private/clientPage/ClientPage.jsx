import ClientProjectTable from "./ClientProjectTable";
import SideBar from "../../../components/shared/SideBar";

const ClientPage = () => {
  return (
    <SideBar>
      <main>
        <ClientProjectTable></ClientProjectTable>
      </main>
    </SideBar>
  );
};

export default ClientPage;
