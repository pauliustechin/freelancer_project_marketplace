import { useForm } from "react-hook-form";
import { IoClose } from "react-icons/io5";
import InputField from "../../../../components/shared/InputField";
import { useEffect } from "react";

const BidForm = ({ modal, setModal, defaultValues }) => {
  const {
    register,
    reset,
    handleSubmit,
    formState: { errors },
  } = useForm();

  useEffect(() => {
    if (defaultValues) {
      reset(defaultValues);
    }
  }, [defaultValues, reset]);


  if (!modal) return null;

  const handleClose = () => {
    setModal(null);
  };

  const handleConfirm = () => {
    modal.onConfirm?.();
    setModal(null);
  };

  return (
    <dialog open className="modal">
      <div className="modal-box p-0">
        <div className="bg-slate-500">
          <div className="flex justify-between items-center p-4">
            <h3 className="font-medium">{modal.title}</h3>
            <IoClose
              className="text-2xl text-gray-400 cursor-pointer"
              onClick={handleClose}
            />
          </div>
          <hr className="text-gray-300" />
        </div>
        <div className="modal-action flex flex-col p-6">
          <p className="text-lg text-slate-800 font-bold">{modal.message}</p>
          <form onSubmit={handleSubmit(handleConfirm)}>
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
                onClick={handleClose}
              >
                Cancel
              </button>
              <button
                type="submit"
                className="my-btn-primary w-fit bg-cyan-600 text-white "
              >
                {modal.confirmButton}
              </button>
            </div>
          </form>
        </div>
      </div>
    </dialog>
  );
};

export default BidForm;
