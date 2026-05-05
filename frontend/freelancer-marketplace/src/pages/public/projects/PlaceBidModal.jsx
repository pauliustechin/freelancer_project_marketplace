import InputField from "../../../components/shared/InputField";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import useBidsStore from "../../../store/bidsStore";
import { useParams } from "react-router";

const PlaceBidModal = ({ open, setOpen }) => {

  const navigate = useNavigate();
  const { placeBid } = useBidsStore();
  const { projectId } = useParams("id")

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm();


  if(open) {
    document.getElementById('my_modal_1').showModal()
  }

  const onSubmit = (formData) => {
    reset();
    placeBid(projectId, navigate, formData);
  }
  
  return (
    <>
      <dialog id="my_modal_1" className="modal">
        <div className="modal-box">
          <div className="modal-action flex flex-col">
          <h3 className="font-bold text-lg">Place a bid</h3>
          <form method="dialog">
            <InputField 
              register={register}
              id="amount"
              type="number"
              placeholder="amount"
              required
              errors={errors}
            />
          </form>
          </div>
          <div className="modal-action">
            <form method="dialog">
            <div className="flex gap-2 font-bold text-white justify-end mt-6">
              <button 
                className="btn btn-primary w-20 bg-green-400" 
                onClick={handleSubmit(onSubmit)}
              >Apply</button>
              <button className="btn btn-primary w-20 bg-slate-400" onClick={setOpen(false)}>Close</button>
            </div>
            </form>
          </div>
        </div>
      </dialog>
    </>
  ) 

}

export default PlaceBidModal;