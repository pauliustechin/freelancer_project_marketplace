import BidForm from "./BidForm";

const EditBidModal = ({ editModal, setEditModal }) => {

  return (
      <BidForm modal={editModal} setModal={setEditModal} defaultValues={editModal?.bid}></BidForm>
  )
}

export default EditBidModal