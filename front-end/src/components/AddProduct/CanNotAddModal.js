import React, {Component} from "react";
import {Modal} from 'antd'

class CanNotAddModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            visible: false,
        };
    }

    handleOk = () => {
        this.props.handleCancel();
    }

    render() {
        const { visible, onClose } = this.props;

        return (
            <Modal
                title="商品名称已存在，请输入新的商品名称"
                visible={visible}
                onOk={this.handleOk}
                onCancel={onClose}>
            </Modal>
        );
    }
}

export default CanNotAddModal;