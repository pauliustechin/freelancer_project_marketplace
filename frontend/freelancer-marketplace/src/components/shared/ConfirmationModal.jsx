import { useRef } from "react";
import { useEffect } from "react";

const ConfirmationModal = ({ open, setOpen, message, setStatus }) => {

  const modalRef = useRef(null);

  useEffect(() => {
    if (open) {
      modalRef.current?.showModal();
    }
  }, [open]);
  
  return (
    <>
      <dialog ref={modalRef} className="modal">
        <div className="modal-box">
          <div className="modal-action flex flex-col">
            <h3 className="font-bold text-lg">{message}</h3>
              <div className="flex gap-2 font-bold text-white justify-end mt-6">
                <button 
                  type="submit"
                  className="btn btn-primary w-20 bg-green-400" 
                  onClick={() => {
                    setStatus(true);
                    modalRef.current?.close();
                  }}
                >Apply</button>
                <button 
                  type="button" 
                  className="btn btn-primary w-20 bg-slate-400" 
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