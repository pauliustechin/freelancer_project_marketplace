import { useRef } from "react";
import { useEffect } from "react";
import { ConfirmationStatus } from "../../enums/confirmationStatus";
import { IoClose } from "react-icons/io5";

const ConfirmationModal = ({
  open,
  setOpen,
  title,
  message,
  setStatus,
  confirmButton,
  rejectButton,
}) => {
  const modalRef = useRef(null);

  useEffect(() => {
    if (open) {
      modalRef.current?.showModal();
    }
  }, [open]);

  return (
    <>
      <dialog ref={modalRef} className="modal text-center">
        <div className="modal-box p-0">
          <div className="bg-gray-200">
            <div className="flex justify-between items-center p-4">
              <h3 className="font-medium text-slate-800">{title}</h3>
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
          <div className="modal-action flex flex-col p-4 m-0">
            <h3 className="font-medium text-lg text-slate-800">{message}</h3>

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

              {rejectButton && (
                <button
                  type="submit"
                  className="btn btn-primary w-20 bg-rose-400 border-none"
                  onClick={() => {
                    setStatus(ConfirmationStatus.REJECTED);
                    setOpen(false);
                    modalRef.current?.close();
                  }}
                >
                  {rejectButton}
                </button>
              )}

              {confirmButton && (
                <button
                  type="submit"
                  className="my-btn-primary w-fit bg-cyan-600 text-white "
                  onClick={() => {
                    setStatus(ConfirmationStatus.ACCEPTED);
                    setOpen(false);
                    modalRef.current?.close();
                  }}
                >
                  {confirmButton}
                </button>
              )}
            </div>
          </div>
        </div>
      </dialog>
    </>
  );
};

export default ConfirmationModal;
