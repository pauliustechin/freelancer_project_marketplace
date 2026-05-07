import { useRef } from "react";
import { useEffect } from "react";
import { ConfirmationStatus } from "../../enums/confirmationStatus";

const ConfirmationModal = ({ open, setOpen, message, setStatus, confirmButton, rejectButton }) => {

  const modalRef = useRef(null);

  useEffect(() => {
    if (open) {
      modalRef.current?.showModal();
    }
  }, [open]);
  
  return (
    <>
      <dialog ref={modalRef} className="modal text-center">
        <div className="modal-box">
          <div className="modal-action flex flex-col">
            <h3 className="font-bold text-lg text-slate-800">{message}</h3>
              <div className="flex gap-2 font-bold text-white justify-end mt-6">

                {confirmButton && 
                <button 
                  type="submit"
                  className="btn btn-primary w-20 bg-green-400 border-none" 
                  onClick={() => {
                    setStatus(ConfirmationStatus.ACCEPTED);
                    setOpen(false);
                    modalRef.current?.close();
                  }}
                >{confirmButton}</button>}

                {rejectButton && 
                <button 
                  type="submit"
                  className="btn btn-primary w-20 bg-red-500 border-none" 
                  onClick={() => {
                    setStatus(ConfirmationStatus.REJECTED);
                    setOpen(false);
                    modalRef.current?.close();
                  }}
                >{rejectButton}</button>}

                <button 
                  type="button" 
                  className="btn btn-primary w-20 bg-slate-400 border-none" 
                  onClick={() => {
                    setOpen(false);
                    modalRef.current?.close();
                  }}
                >Close</button>
              </div>
          </div>
        </div>
      </dialog>
    </>
  ) 

}

export default ConfirmationModal;