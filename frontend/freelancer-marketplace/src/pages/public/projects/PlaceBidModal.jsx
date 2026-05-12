import InputField from "../../../components/shared/InputField";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import useBidsStore from "../../../store/bidsStore";
import { useParams } from "react-router";
import { useRef } from "react";
import { useEffect } from "react";
import { IoClose } from "react-icons/io5";

const PlaceBidModal = ({ open, setOpen }) => {
  const navigate = useNavigate();
  const { placeBid } = useBidsStore();
  const { projectId } = useParams();
  const modalRef = useRef(null);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (formData) => {
    placeBid(projectId, navigate, formData);
  };

  useEffect(() => {
    if (open) {
      modalRef.current?.showModal();
    }
  }, [open]);

  return (
    <>
      <dialog ref={modalRef} className="modal">
        <div className="modal-box p-0">
          <div className="bg-gray-200">
            <div className="flex justify-between items-center p-4">
              <h3 className="font-medium">Place a bid</h3>
              <IoClose
                className="text-2xl text-gray-400 cursor-pointer"
                onClick={() => {
                  setOpen(false);
                  modalRef.current?.close();
                }}
              />
            </div>
            <hr className="text-gray-300" />
          </div>
          <div className="modal-action flex flex-col p-6">
            <form onSubmit={handleSubmit(onSubmit)}>
              <InputField
                register={register}
                id="amount"
                type="number"
                placeholder="amount"
                required
                errors={errors}
                theme={"input-light no-spinner border border-slate-300"}
              />
              <div className="flex gap-2 font-bold text-white justify-end mt-6">
                <button
                  type="button"
                  className="my-btn-secondary"
                  onClick={() => {
                    setOpen(false);
                    modalRef.current?.close();
                  }}
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="my-btn-primary w-fit bg-cyan-600 text-white "
                >
                  Submit bid
                </button>
              </div>
            </form>
          </div>
        </div>
      </dialog>
    </>
  );
};

export default PlaceBidModal;
