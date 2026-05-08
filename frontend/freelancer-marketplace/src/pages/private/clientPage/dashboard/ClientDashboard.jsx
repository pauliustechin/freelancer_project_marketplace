import ClientProjectTable from "../projects/ClientProjectTable"

const ClientDashboard = () => {
  return (
    <div className="text-slate-200 bg-slate-400 min-h-screen p-8">
      <ClientProjectTable></ClientProjectTable>
    </div>
  )
}

export default ClientDashboard